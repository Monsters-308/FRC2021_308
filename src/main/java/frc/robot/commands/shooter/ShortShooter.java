package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;;

/**
 * A simple command that runs the shooter in reverse with the {@link ShooterSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ShortShooter extends CommandBase {
  // The subsystem the command runs on
  private final ShooterSubsystem m_shooterSubsystem;
  private int loopcount;

  public ShortShooter(ShooterSubsystem subsystem) {
    m_shooterSubsystem = subsystem;
    addRequirements(m_shooterSubsystem);
  }

  @Override
  public void initialize() {
    m_shooterSubsystem.shortShooter();
    loopcount = 0;
  }

  @Override
  public void execute(){
    if(loopcount++ % 10 == 0){
      m_shooterSubsystem.updatevalues();
    }
  }


  @Override
  public boolean isFinished() {
    if((double)m_shooterSubsystem.getShooterVelocity() > Constants.ShooterConstants.kShortShotRPM){
      return true;
    }else{
      return false;
    }
  }
}
