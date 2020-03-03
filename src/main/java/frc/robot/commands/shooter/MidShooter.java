package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;;

/**
 * A simple command that runs the shooter with the {@link ShooterSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class MidShooter extends CommandBase {
  // The subsystem the command runs on
  private final ShooterSubsystem m_shooterSubsystem;

  public MidShooter(ShooterSubsystem subsystem) {
    m_shooterSubsystem = subsystem;
    addRequirements(m_shooterSubsystem);
  }

  @Override
  public void initialize() {
    m_shooterSubsystem.midShooter();
  }

  @Override
  public void execute(){
  }


  @Override
  public boolean isFinished() {
    if((double)m_shooterSubsystem.getShooterVelocity() > Constants.ShooterConstants.kMidShotRPM){
      return true;
    }else{
      return false;
    }
  }
}
