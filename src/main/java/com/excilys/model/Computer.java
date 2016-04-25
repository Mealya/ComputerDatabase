package com.excilys.model;

import java.sql.Timestamp;

public class Computer {
    private long id;
    private String name;
    private Timestamp intro;
    private Timestamp disco;
    private Company comp;

    public Computer(long id, String name, Timestamp intro, Timestamp disco,
            Company comp) {
        super();
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.disco = disco;
        this.comp = comp;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", intro=" + intro
                + ", disco=" + disco + ", comp=" + comp + "]";
    }

    public Computer() {

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

    public Company getComp() {
        return comp;
    }

    public void setComp(Company comp) {
        this.comp = comp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comp == null) ? 0 : comp.hashCode());
        result = prime * result + ((disco == null) ? 0 : disco.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((intro == null) ? 0 : intro.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Computer other = (Computer) obj;
        if (comp == null) {
            if (other.comp != null) {
                return false;
            }
        } else if (!comp.equals(other.comp)) {
            return false;
        }

        if (disco == null) {
            if (other.disco != null) {
                return false;
            }
        } else if (!disco.equals(other.disco)) {
            return false;
        }

        if (id != other.id) {
            return false;
        }

        if (intro == null) {
            if (other.intro != null) {
                return false;
            }
        } else if (!intro.equals(other.intro)) {
            return false;
        }

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

}
