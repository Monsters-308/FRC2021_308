package frc.robot.commands.wof;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.WOFSubsystem;;

/**
 * A simple command that forwards the WOF with the {@link WOFSubsystem}. Written
 * explicitly for pedagogical purposes. Actual code should inline a command this
 * simple with {@link edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class WOFSpinThree extends CommandBase {
  // The subsystem the command runs on
  private final WOFSubsystem m_wofSubsystem;
  private double m_getFPGATimestamp;

  public WOFSpinThree(WOFSubsystem subsystem) {
    m_wofSubsystem = subsystem;
    addRequirements(m_wofSubsystem);
  }

  @Override
  public void initialize() {
    m_getFPGATimestamp = Timer.getFPGATimestamp();
    m_wofSubsystem.forwardWOF();
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted){
    m_getFPGATimestamp = 0;
  }
  
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() <= m_getFPGATimestamp + 3.5) {
      return false;
    } else if (Timer.getFPGATimestamp() > m_getFPGATimestamp + 3.5) {
      m_wofSubsystem.stopWOF();
      return true;
    }else{
      return false;
    }
  }
}
