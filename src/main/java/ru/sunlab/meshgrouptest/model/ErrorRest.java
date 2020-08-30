package ru.sunlab.meshgrouptest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "error_rest")
public class ErrorRest {

    public ErrorRest(String msg, Timestamp timestamp) {
        this.msg = msg;
        this.created = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msg", nullable = false)
    private String msg;

    @Column(name = "created", nullable = false)
    private Timestamp created;

}
