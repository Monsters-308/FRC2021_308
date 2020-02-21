/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;


public class DriveAim extends CommandBase {
  private Solenoid m_visionSolenoid = new Solenoid(Constants.DriveConstants.kLEDRingModule,Constants.DriveConstants.kLEDRingPort);
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  // private double m_targetYawError;
  private NetworkTableEntry m_visionYaw;
  private NetworkTableEntry m_isDriverMode;
  private NetworkTable m_visionTable;
  private double KpRot = -0.1;
  private double rotationError;
  private double angleTolerance = 5.0;
  private double rotationAdjust;
  private double constantForce = 0.05;
  /**
   * Creates a new DriveAim.
   */
  public DriveAim(DriveSubsystem subsystem, DoubleSupplier forward) {
    m_forward = forward;
    m_drive = subsystem;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_visionSolenoid.set(true);
    m_visionTable = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("MyCamName");
    m_visionYaw = m_visionTable.getEntry("yaw");
    m_isDriverMode = m_visionTable.getEntry("driver_mode");
    m_isDriverMode.setBoolean(true);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rotationAdjust = 0;
    rotationError = m_visionYaw.getDouble(0.0);
    if(rotationError > angleTolerance){
      rotationAdjust = KpRot*rotationError+constantForce;
    }else{
      if(rotationError < angleTolerance){
        rotationAdjust = KpRot*rotationError-constantForce;
      }
    }

    m_drive.arcadeDrive(m_forward.getAsDouble()/-1.05,rotationAdjust);

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_isDriverMode.setBoolean(false); // Set Camera Back to Normal
    m_visionSolenoid.set(false);// turn off LED
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
