package View;

import Model.SkillTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillTreeListener implements ActionListener {
    private SkillTree skills;

    public  SkillTreeListener(SkillTree skills){
        this.skills=skills;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        skills.upLife();
    }
}
