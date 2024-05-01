package com.app.vp.wookiebooks.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Model class:
 * Contain: title, description, author (user model), cover image and price
 * Constraints:
 * -bookId (generated sequence)
 * -title (unique value)
 * */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(
        name = "tbl_book",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "title_unique",
                        columnNames = "title"
                )
        })
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "book_id",
            updatable = false
    )
    private Long bookId;
    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String title;
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @Column(
            name = "cover_image",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String coverImage;
    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "DOUBLE"
    )
    private double price;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Book book = (Book) o;
        return getBookId() != null && Objects.equals(getBookId(), book.getBookId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
