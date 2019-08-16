package services;

import domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActivityRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;


    public Collection<Activity> findAll(){
        Collection<Activity> res;
        res = this.activityRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Activity findOne(int activityId){
        Activity res;
        res = this.activityRepository.findOne(activityId);
        Assert.notNull(res);
        return res;
    }

}
