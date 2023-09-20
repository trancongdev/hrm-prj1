package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.Data_Access_Layer.RewardPunishRepository;
import com.hrm.hrm.Data_Access_Layer.TimekeepingRepository;
import com.hrm.hrm.Data_Access_Layer.UserRepository;
import com.hrm.hrm.dto.MonthAndYear;
import com.hrm.hrm.dto.PayrollSheet;
import com.hrm.hrm.dto.RewardPunishDto;
import com.hrm.hrm.dto.TimekeepingDto;
import com.hrm.hrm.entity.RewardPunish;
import com.hrm.hrm.entity.Timekeeping;
import com.hrm.hrm.entity.User;
import com.hrm.hrm.form.FormCreateTimekeeping;
import com.hrm.hrm.form.FormTimeKeeping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional
@Service
public class TimekeepingService implements ITimekeepingService{
    @Autowired
    TimekeepingRepository timekeepingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RewardPunishRepository rewardPunishRepository;

    @Override
    public List<FormTimeKeeping> getAllTimekeeping(String search, LocalDate startTime, LocalDate endTime) {

        List<LocalDate> monthAndYears = timekeepingRepository.selectDate(search,startTime,endTime);
        List<MonthAndYear> monthAndYearList = new ArrayList<>();
        for(LocalDate my:monthAndYears){
            MonthAndYear monthAndYear = new MonthAndYear(my.getMonth().getValue(),my.getYear());
            monthAndYearList.add(monthAndYear);
        }
        List<User> userList = timekeepingRepository.selectUser(search);
        List<FormTimeKeeping> formTimeKeepingList = new ArrayList<>();
        for(MonthAndYear my:monthAndYearList){
            for(User user : userList){
                List<Timekeeping> timekeepings = timekeepingRepository.getTimekeepingByDateAndUserId(my.getMonth(),my.getYear(),user.getId());
                List<TimekeepingDto> timekeepingDtoList = new ArrayList<>();
                if(timekeepings.size()!=0) {
                    for (Timekeeping timekeeping : timekeepings) {
                        TimekeepingDto timekeepingDto = new TimekeepingDto(timekeeping);
                        timekeepingDtoList.add(timekeepingDto);
                    }
                    Long rewardPunish = timekeepingRepository.totalRewarPunish(user.getId(), my.getMonth(), my.getYear());
                    if(rewardPunish==null){
                        rewardPunish=0L;
                    }
                    FormTimeKeeping formTimeKeeping = new FormTimeKeeping(user, my, timekeepingDtoList,rewardPunish );
                    formTimeKeepingList.add(formTimeKeeping);
                }
            }
        }
        return formTimeKeepingList;
    }




    @Override
    public List<FormTimeKeeping> getAllTimekeepingByUser(int userId, LocalDate startTime, LocalDate endTime) {
        List<LocalDate> monthAndYears = timekeepingRepository.selectDateByUser(userId,startTime,endTime);
        List<MonthAndYear> monthAndYearList = new ArrayList<>();
        for(LocalDate my:monthAndYears){
            MonthAndYear monthAndYear = new MonthAndYear(my.getMonth().getValue(),my.getYear());
            monthAndYearList.add(monthAndYear);
        }
        User user = userRepository.findById(userId);
        List<FormTimeKeeping> formTimeKeepingList = new ArrayList<>();
        for(MonthAndYear my:monthAndYearList){
                List<Timekeeping> timekeepings = timekeepingRepository.getTimekeepingByDateAndUserId(my.getMonth(),my.getYear(),user.getId());
                List<TimekeepingDto> timekeepingDtoList = new ArrayList<>();
                if(timekeepings.size()!=0) {
                    for (Timekeeping timekeeping : timekeepings) {
                        TimekeepingDto timekeepingDto = new TimekeepingDto(timekeeping);
                        timekeepingDtoList.add(timekeepingDto);
                    }
                    Long rewardPunish = timekeepingRepository.totalRewarPunish(user.getId(), my.getMonth(), my.getYear());
                    if(rewardPunish==null){
                        rewardPunish=0L;
                    }
                    FormTimeKeeping formTimeKeeping = new FormTimeKeeping(user, my, timekeepingDtoList,rewardPunish );
                    formTimeKeepingList.add(formTimeKeeping);
                }
        }
        return formTimeKeepingList;
    }

    @Override
    public List<PayrollSheet> PayrollSheets(int month, int year, int userId) {
        List<Timekeeping> timekeepings = timekeepingRepository.selectPayrollByUser(month,year,userId);
        List<PayrollSheet> payrollSheets = new ArrayList<>();

        for(Timekeeping timekeeping:timekeepings){
            List<RewardPunish> rewardPunishList = rewardPunishRepository.findAllByUser_IdAndDay(userId,timekeeping.getWorkday());
            List<RewardPunishDto> rewardPunishDtoList = new ArrayList<>();
            for(RewardPunish rewardPunish:rewardPunishList){
                RewardPunishDto rewardPunishDto = new RewardPunishDto(rewardPunish);
                rewardPunishDtoList.add(rewardPunishDto);
            }
            TimekeepingDto timekeepingDto = new TimekeepingDto(timekeeping);
            PayrollSheet payrollSheet = new PayrollSheet(timekeepingDto,rewardPunishDtoList,timekeeping.getUser().getSalary());
            payrollSheets.add(payrollSheet);
        }


        return payrollSheets;
    }

    @Override
    public void CreateTimekeeping(FormCreateTimekeeping formCreateTimekeeping) {
        User user = userRepository.findById(formCreateTimekeeping.getUserId());
        Timekeeping timekeeping = formCreateTimekeeping.toTimekeeping();
        timekeeping.setUser(user);
        timekeepingRepository.saveAndFlush(timekeeping);
    }
}
