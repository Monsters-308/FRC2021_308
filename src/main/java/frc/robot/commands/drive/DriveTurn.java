package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTurn extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_heading;
  private final double m_speed;
  private final double m_headingError = 0.1;

  /**
   * Creates a new DriveDistance.
   *
   * @param heading The heading to turn too
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveTurn(double heading, double speed, DriveSubsystem drive) {
    m_heading = heading;
    m_speed = speed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.resetGyro();
    m_drive.arcadeDrive(0.0, m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
      if(m_drive.getGyroHeading() > m_heading - m_headingError && m_drive.getGyroHeading() < m_heading + m_headingError){
          return true;
      }else{
        m_drive.arcadeDrive(0.0, m_speed);
        return false;
      }

  }
}
