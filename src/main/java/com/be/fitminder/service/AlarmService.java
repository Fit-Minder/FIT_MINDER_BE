package com.be.fitminder.service;

import com.be.fitminder.domain.Alarm;
import com.be.fitminder.domain.Member;
import com.be.fitminder.dto.AlarmRequestDTO;
import com.be.fitminder.job.AlarmJob;
import com.be.fitminder.repository.AlarmRepository;
import com.be.fitminder.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final Scheduler scheduler;

    @Transactional
    public Long createAlarm(AlarmRequestDTO alarmRequestDTO, Long memberId) throws SchedulerException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);

        log.info(alarmRequestDTO.getDays());
        Alarm alarm = Alarm.of(alarmRequestDTO, member);
        Alarm savedAlarm = alarmRepository.save(alarm);

        scheduleAlarm(savedAlarm);
        log.info("알람 생성 완료");
        return savedAlarm.getId();
    }

    @Transactional
    public void updateAlarm(AlarmRequestDTO alarmRequestDTO, Long memberId) throws SchedulerException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoSuchElementException::new);
        Alarm alarm = member.getAlarm();
        alarm.updateAlarm(alarmRequestDTO, member);

        scheduler.deleteJob(new JobKey("alarmJob" + alarm.getId()));

        scheduleAlarm(alarm);
        log.info("알람 생성 완료");
    }

    @Transactional
    public void deleteAlarm(Long alarmId) throws SchedulerException {
        scheduler.deleteJob(new JobKey("alarmJob" + alarmId));
        alarmRepository.deleteById(alarmId);
    }

    public void scheduleAlarm(Alarm alarm) throws SchedulerException {
        List<String> daysOfWeek = Arrays.asList(alarm.getDays().split(","));

        daysOfWeek.forEach(day -> {
            JobDetail jobDetail = JobBuilder.newJob(AlarmJob.class)
                    .withIdentity("alarmJob" + alarm.getMember().getId() + day + UUID.randomUUID())
                    .usingJobData("memberId", alarm.getMember().getId())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("alarmTrigger" + alarm.getMember().getId() + day + UUID.randomUUID())
                    .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(getDayOfWeek(day), alarm.getStartTime().getHour(), alarm.getStartTime().getMinute())
                            .inTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
                            .withMisfireHandlingInstructionDoNothing())
                    .build();

            try {
                scheduler.scheduleJob(jobDetail, trigger);
                log.info("Scheduled initial alarm for memberId: " + alarm.getMember().getId() + " on " + day + " at " + alarm.getStartTime());

                LocalTime nextStartTime = alarm.getStartTime().plusMinutes(alarm.getIntervalInMinutes());
                while (!nextStartTime.isAfter(alarm.getEndTime().minusMinutes(1))) {
                    JobDetail repeatedJobDetail = JobBuilder.newJob(AlarmJob.class)
                            .withIdentity("repeatedAlarmJob" + alarm.getMember().getId() + day + nextStartTime + UUID.randomUUID())
                            .usingJobData("memberId", alarm.getMember().getId())
                            .build();

                    Trigger repeatedTrigger = TriggerBuilder.newTrigger()
                            .withIdentity("repeatedAlarmTrigger" + alarm.getMember().getId() + day + nextStartTime + UUID.randomUUID())
                            .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(getDayOfWeek(day), nextStartTime.getHour(), nextStartTime.getMinute())
                                    .inTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
                                    .withMisfireHandlingInstructionDoNothing())
                            .build();

                    scheduler.scheduleJob(repeatedJobDetail, repeatedTrigger);
                    log.info("Scheduled repeated alarm for memberId: " + alarm.getMember().getId() + " on " + day + " at " + nextStartTime);

                    nextStartTime = nextStartTime.plusMinutes(alarm.getIntervalInMinutes());
                }
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String createCronExpression(String dayOfWeek, LocalTime startTime) {
        int hour = startTime.getHour();
        int minute = startTime.getMinute();
        return String.format("0 %d %d ? * %s", minute, hour, dayOfWeek);
    }

    private int getDayOfWeek(String day) {
        return switch (day) {
            case "SUN" -> DateBuilder.SUNDAY;
            case "MON" -> DateBuilder.MONDAY;
            case "TUE" -> DateBuilder.TUESDAY;
            case "WED" -> DateBuilder.WEDNESDAY;
            case "THU" -> DateBuilder.THURSDAY;
            case "FRI" -> DateBuilder.FRIDAY;
            case "SAT" -> DateBuilder.SATURDAY;
            default -> throw new IllegalArgumentException("Invalid day of week: " + day);
        };
    }
}

