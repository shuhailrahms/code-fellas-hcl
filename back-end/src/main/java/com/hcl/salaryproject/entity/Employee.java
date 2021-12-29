package com.hcl.salaryproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String callingname;
    private String fullname;
    private String dobirth;
    private String nic;
    private String address;
    private String mobile;
    private String land;
    private String dorecruit;
    private Date tocreation;
    private String gender;
    private String civilstatus;
    private Integer designationId;
    private String nametitle;
    private String email;
}

//{
//        "callingname":"Shuhail",
//        "fullname":"Shuhail Abdul Rahman",
//        "dobirth":"2012-04-23T18:25:43.511Z",
//        "nic":"961740592V",
//        "address":"170/1C, padiliyathuduwa Road, Hunupitiya, Wattala",
//        "mobile":"0770684478",
//        "land":"0114996689",
//        "dorecruit":"2012-04-23T18:25:43.511Z",
//        "tocreation": "2012-04-23T18:25:43.511Z",
//        "gender":"Male",
//        "civilstatus":"Single",
//        "designation":"Software Engineer",
//        "nametitle":"Mr.",
//        "email":"shuhail1996@gmail.com"
//}
