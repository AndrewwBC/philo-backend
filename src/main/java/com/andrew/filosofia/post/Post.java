package com.andrew.filosofia.post;


import com.andrew.filosofia.categorie.Categorie;
import com.andrew.filosofia.post.dto.PostDTO;
import com.andrew.filosofia.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "Posts")
@Table(name = "posts")
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    @NotNull
    private String content;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Post(String content, LocalDateTime creationDate, User user){
        this.content = content;
        this.creationDate = creationDate;
        this.user = user;
    }

    public static Post fromDTO(PostDTO postDTO, User user){
        return new Post(postDTO.content(), postDTO.creationDate(), user);
    }

    @ManyToMany
    @JoinTable(name = "post_categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Categorie> categories;
}
