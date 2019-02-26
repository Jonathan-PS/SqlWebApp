package no.noroff.SqlWebApp.models;

import no.noroff.SqlWebApp.SqlWebApplication;


public class Relationship {
    private final int rID;
    private final int p1;
    private final int p2;
    private String p1p2;
    private String p2p1;

    private String p1Name;
    private String p2Name;

    //Constructor
    public Relationship(int rID, int p1, int p2, String p1p2, String p2p1) {
        this.rID = rID;
        this.p1 = p1;
        this.p2 = p2;
        this.p1p2 = p1p2;
        this.p2p1 = p2p1;

        setPersonsNames(p1,p2);
    }

    /* GETTERS */
    public int getrID () {
        return rID;
    }
    public int getP1() {
        return p1;
    }
    public int getP2() {
        return p2;
    }
    public String getP1p2() {
        return p1p2;
    }
    public String getP2p1() {
        return p2p1;
    }
    public String getP1Name() {
        return p1Name;
    }
    public String getP2Name() {
        return p2Name;
    }

    /* SETTERS*/
    public void setP1p2(String p1p2) {
        this.p1p2 = p1p2;
    }
    public void setP2p1(String p2p1) {
        this.p2p1 = p2p1;
    }

    private void setPersonsNames(int p1, int p2){
        p1Name = SqlWebApplication.conn.selectPerson(p1).getName();
        p2Name = SqlWebApplication.conn.selectPerson(p2).getName();
    }
}
