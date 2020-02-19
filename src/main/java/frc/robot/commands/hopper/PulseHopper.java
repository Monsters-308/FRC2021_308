package frc.robot.commands.hopper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;;

/**
 * A simple command that runs the intake forward with the {@link HopperSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class PulseHopper extends CommandBase {
  // The subsystem the command runs on
  private final HopperSubsystem m_hopperSubsystem;
  public static double getFPGATimestamp;
  public void start(){

  }
  public PulseHopper(HopperSubsystem subsystem) {
    m_hopperSubsystem = subsystem;
    addRequirements(m_hopperSubsystem);
  }

  @Override
  public void execute() {
    if (Timer.getFPGATimestamp() > 1.0){
      m_hopperSubsystem.forwardHopper();
    }
    else{
      m_hopperSubsystem.reverseHopper();
    }
  }

  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() > 1.25){
      return true;
    }
    else{
      return false;
    }
  }
}
