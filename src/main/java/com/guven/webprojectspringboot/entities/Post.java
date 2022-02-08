package com.guven.webprojectspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //ilgili posttu çektiğimde ilgili useri bana getirmene gerek yok
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  //bir user silindiğinde tüm postları silinsin
    @JsonIgnore
    User user;

    String title;

    @Column(length = 1000)
    String text;


}
