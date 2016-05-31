package com.excilys.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "computer")
public class Computer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "name")
    @Size(min=1, max=45)
    private String name;
    
    @Column(name = "introduced")
    private Timestamp intro;
    
    @Column(name = "discontinued")
    private Timestamp disco;
    
    @ManyToOne
    private Company company;

    /**
     * Create an empty computer.
    
    public Computer() {

    } */

    /**
     * Create a Computer with all parameters.
     * @param id
     *            The id of the computer
     * @param name
     *            The name of the computer
     * @param intro
     *            The introduced date of the computer
     * @param disco
     *            The discontinued date of the computer
     * @param company
     *            The company linked to the computer
     */
    /*public Computer(long id, String name, Timestamp intro, Timestamp disco,
            Company comp) {
        super();
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.disco = disco;
        this.comp = comp;
    }*/

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", intro=" + intro
                + ", disco=" + disco + ", comp=" + company + "]";
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
        return company;
    }

    public void setComp(Company comp) {
        this.company = comp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
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
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
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
