package PokemonBeyond.Skill.aggregate;

import java.io.Serializable;

public class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skillName;
    private int skillPower;

    public Skill() {
    }

    public Skill(String skillName, double skillPower) {
        this.skillName = skillName;
        this.skillPower = (int)skillPower;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillPower() {
        return skillPower;
    }

    public void setSkillPower(double skillPower) {
        this.skillPower = (int)skillPower;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", skillPower=" + skillPower +
                '}';
    }
}
