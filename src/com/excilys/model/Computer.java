package com.excilys.model;

import java.sql.Timestamp;

public class Computer {
    private long id;
    private String name;
    private Timestamp intro;
    private Timestamp disco;
    private int compId;

    public Computer() {

    }

    public Computer(long id, String name, Timestamp intro, Timestamp disco,
            int compId) {
        super();
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.disco = disco;
        this.compId = compId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getIntro() {
        return intro;
    }

    public void setIntro(Timestamp intro) {
        this.intro = intro;
    }

    public Timestamp getDisco() {
        return disco;
    }

    public void setDisco(Timestamp disco) {
        this.disco = disco;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String toString() {
        return "ID : " + id + " Name : " + name + " Introduction : " + intro
                + " Discontinued : " + disco + " Company ID : " + compId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + compId;
        result = prime * result + ((disco == null) ? 0 : disco.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((intro == null) ? 0 : intro.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Computer other = (Computer) obj;
        if (compId != other.compId)
            return false;
        if (disco == null) {
            if (other.disco != null)
                return false;
        } else if (!disco.equals(other.disco))
            return false;
        if (id != other.id)
            return false;
        if (intro == null) {
            if (other.intro != null)
                return false;
        } else if (!intro.equals(other.intro))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
