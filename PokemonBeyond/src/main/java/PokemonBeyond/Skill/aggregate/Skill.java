package PokemonBeyond.Skill.aggregate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Skill extends ArrayList<Skill> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int skillNo;
    private String skillName;
    private int skillPower;

    public Skill() {
    }

    public Skill(int skillNo, String skillName, double skillPower) {
        this.skillNo = skillNo;
        this.skillName = skillName;
        this.skillPower = (int)skillPower;
    }

    public int getSkillNo() {
        return skillNo;
    }

    public void setSkillNo(int skillNo) {
        this.skillNo = skillNo;
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

    public void setSkillPower(double  skillPower) {
        this.skillPower = (int)skillPower;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Skill skill = (Skill) object;
        return skillNo == skill.skillNo && skillPower == skill.skillPower && Objects.equals(skillName, skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillNo, skillName, skillPower);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillNo=" + skillNo +
                ", skillName='" + skillName + '\'' +
                ", skillPower=" + skillPower +
                '}';
    }
}
