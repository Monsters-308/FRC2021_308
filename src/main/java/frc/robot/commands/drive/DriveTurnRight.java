package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTurnRight extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_heading = 359;
  private final double m_leftSpeed;
  private final double m_rightSpeed;
  private final double m_headingError = 5;

  /**
   * Creates a new DriveDistance.
   *
   * @param heading The heading to turn too
   * @param speed   The speed at which the robot will drive
   * @param drive   The drive subsystem on which this command will run
   */
  public DriveTurnRight(double leftSpeed, double rightSpeed, DriveSubsystem drive) {
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.resetGyro();
    m_drive.tankDrive(m_leftSpeed, m_rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (m_drive.getGyroHeading() > m_heading - m_headingError
        && m_drive.getGyroHeading() < m_heading + m_headingError) {
      return true;
    } else {
      m_drive.tankDrive(m_leftSpeed, m_rightSpeed);
      return false;
    }

  }
}
