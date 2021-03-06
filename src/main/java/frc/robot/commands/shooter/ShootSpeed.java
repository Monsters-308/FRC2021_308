/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootSpeed extends CommandBase {
  private final ShooterSubsystem m_shooterSubsystem;
  private double m_speedTarget;

  /**
   * Creates a new ShootSpeed.
   */
  public ShootSpeed(ShooterSubsystem subsystem, double speed) {
    m_speedTarget = speed;
    m_shooterSubsystem = subsystem;
    addRequirements(m_shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooterSubsystem.speedControlShooter(m_speedTarget);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    double checkVelocity = (double)m_shooterSubsystem.getShooterVelocity() - (double)m_speedTarget;

    if(Math.abs(checkVelocity) <= Constants.ShooterConstants.kvelocityError){
      return true;
    }else{
      return false;
    }
  }
}
