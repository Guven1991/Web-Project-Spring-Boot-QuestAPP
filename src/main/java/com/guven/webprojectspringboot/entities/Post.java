package com.guven.webprojectspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)  //ilgili posttu çektiğimde ilgili useri bana getirmene gerek yok
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  //bir user silindiğinde tüm postları silinsin
    User user;

    String title;

    @Column(length = 1000)
    String text;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;


}
