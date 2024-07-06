SELECT DISTINCT j.job_id,
        jd.benefits,
        jd.work_description,
        jd.work_hours,
        jd.work_requirements,
        jd.work_welcome,
        jd.work_trial_time,
        jd.work_year_salary,
        jd.work_good_points,
        jd.work_need_person,
        jd.work_environment,
        i.industry_name,
        s.skill_id,
        s.skill_name,
        ci.job_img_id,
        ci.job_img,
        ci.job_detail_id,
        j.job_id,
        j.job_name,
        c.company_name,
        j.japanese_level,
        j.min_salary,
        j.max_salary,
        j.location,
        j.tag1,
        j.tag2,
        j.tag3
FROM job_detail jd
    LEFT JOIN job j ON jd.job_id = j.job_id
    LEFT JOIN company c ON c.company_id = j.company_id
    LEFT JOIN company_img ci ON ci.job_detail_id = jd.job_detail_id
    LEFT JOIN skill_job sj ON sj.job_id = j.job_id
    LEFT JOIN skill s ON s.skill_id = sj.skill_id
    LEFT JOIN industry i ON i.industry_id = j.industry_id
    LEFT JOIN foreign_ratio fr ON fr.job_id = j.job_id

SELECT jd.job_id,
        jd.benefits,
        jd.work_description,
        jd.work_hours,
        jd.work_requirements,
        jd.work_welcome,
        jd.work_trial_time,
        jd.work_year_salary,
        jd.work_good_points,
        jd.work_need_person,
        jd.work_environment,
        ci.job_img_id,
        ci.job_img,
        ci.job_detail_id,
        j.job_id,
        j.job_name,
        c.company_name,
        j.japanese_level,
        j.min_salary,
        j.max_salary,
        j.location,
        j.tag1,
        j.tag2,
        j.tag3    
FROM job_detail jd    
     INNER JOIN job j ON jd.job_id = j.job_id
     INNER JOIN company c ON c.company_id = j.company_id
     INNER JOIN company_img ci ON ci.job_detail_id = jd.job_detail_id
            
