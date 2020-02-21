package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonDelay extends CommandBase {
    private double m_delayTime;
    private double m_getFPGATimstamp;
    public AutonDelay (double delayTime){
        m_delayTime = delayTime;
    }

    @Override
    public void initialize(){
        m_getFPGATimstamp = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished(){
        if(Timer.getFPGATimestamp() > m_getFPGATimstamp+m_delayTime){
            return false;
        }else{
            return true;
        }
    }
}