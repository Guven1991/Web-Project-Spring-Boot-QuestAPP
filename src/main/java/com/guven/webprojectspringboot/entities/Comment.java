package com.guven.webprojectspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //ilgili posttu çektiğimde ilgili useri bana getirmene gerek yok
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  //bir user silindiğinde tüm postları silinsin
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)  //ilgili posttu çektiğimde ilgili useri bana getirmene gerek yok
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  //bir user silindiğinde tüm postları silinsin
    @JsonIgnore
    User user;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}
