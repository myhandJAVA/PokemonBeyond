package PokemonBeyond.Skill.repository;

import PokemonBeyond.Skill.aggregate.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SkillRepository {

    private ArrayList<Skill> skillList = new ArrayList<>();

    public SkillRepository() {

        File fileInput = new File("src/main/java/pokemonBeyond/text/skill.txt");
        File fileOutput = new File("src/main/java/pokemonBeyond/skill/db/skills.dat");

        loadSkills(fileInput);

        saveSkills(fileOutput, skillList);

        for (Skill skill : skillList) {
//            System.out.println("skill = " + skill);
        }

    }

    public void loadSkills(File fileInput) {
        File file = new File(String.valueOf(fileInput));

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    StringTokenizer st = new StringTokenizer(line, ",");
                    if (st.countTokens() >= 2) {
                        String skillName = st.nextToken();
                        double skillPower = Double.parseDouble(st.nextToken());
//                        System.out.println("Loaded: " + skillName + " " + skillPower);

                        Skill skill = new Skill(skillName, skillPower);
                        skillList.add(skill);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void saveSkills(File fileOutput, ArrayList<Skill> skillList) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileOutput));

            for( Skill skill: skillList) {
                bw.write(skill.getSkillName() + "," + skill.getSkillPower() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public ArrayList<Skill> selectRandomSkills() {
        return skillList;
    }
}
