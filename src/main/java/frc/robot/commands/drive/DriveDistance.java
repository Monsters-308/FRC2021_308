package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveDistance extends CommandBase {
  private final DriveSubsystem m_drive;
  private double m_distance;
  private double m_speed;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveDistance(double inches, double speed, DriveSubsystem drive) {
    m_distance = inches;
    m_speed = speed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted){
      System.out.println("Auton interrupted");

    }else{
      System.out.println("Normal End");

    }
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if(m_distance > 0){
      if(m_drive.getLeftEncoder().getPosition() < m_distance){
        m_drive.arcadeDrive(m_speed, 0);
        return false;
      }else{
        return true;
      }

    }else if(m_distance < 0){
      if(m_drive.getLeftEncoder().getPosition() > m_distance){
          m_drive.arcadeDrive(m_speed, 0);
          return false;
        }else{
          return true;
        }
  
    }else{
      return true;

    }
  }
}
