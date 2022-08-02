package com.test.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIES")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int cId;
    @Column(name = "MOVIES")
    private String movies;
    @Column(name = "STATUS")
    private String status;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "cId=" + cId + ", Movies=" + movies + ", status=" + status;
    }


}
