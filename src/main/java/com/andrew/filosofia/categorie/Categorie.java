package com.andrew.filosofia.categorie;

import com.andrew.filosofia.post.Post;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;
}

