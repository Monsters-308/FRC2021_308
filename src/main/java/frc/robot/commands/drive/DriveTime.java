package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTime extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_time;
  private final double m_speed;
  private double m_getFPGATimestamp;

  /**
   * Creates a new DriveDistance.
   *
   * @param time The time the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveTime(double time, double speed, DriveSubsystem drive) {
    m_time = time;
    m_speed = speed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_getFPGATimestamp = Timer.getFPGATimestamp();
    m_drive.resetEncoders();
    m_drive.arcadeDrive(m_speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
      if(Timer.getFPGATimestamp() < m_getFPGATimestamp + m_time){
        m_drive.arcadeDrive(m_speed, 0);
        return false;
      }else{
        return true;
      }
  }
}
