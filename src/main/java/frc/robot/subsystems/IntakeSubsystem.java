package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;

/**
 * An intake mechanism actuated by a single {@link Solenoid} and a sintle {@link WPI_TalonSRX}.
 */
public class IntakeSubsystem extends SubsystemBase {
  private final Solenoid m_intakeSolenoid = new Solenoid(IntakeConstants.kIntakeSolenoidModule, IntakeConstants.kIntakeSolenoidPort);
  private final WPI_TalonSRX m_intakeMotor = new WPI_TalonSRX(IntakeConstants.kIntakeMotorCANPort);


  public IntakeSubsystem(){

  }
  /**
   * Extend the Intake.
   */
  public void extendIntake() {
    m_intakeSolenoid.set(true);
  }

  /**
   * Retract the Intake
   */
  public void retractIntake() {
    m_intakeSolenoid.set(false);
  }

  /**
   * Forward Intake.
   */
  public void forwardIntake() {
    m_intakeMotor.set(ControlMode.PercentOutput,IntakeConstants.kIntakeMotorForwardSpeed);
  }

  /**
   * Reverse Intake.
   */
  public void reverseIntake() {
    m_intakeMotor.set(ControlMode.PercentOutput,IntakeConstants.kIntakeMotorReverseSpeed);
  }

  /**
   * Stop Intake.
   */
  public void stopIntake() {
    m_intakeMotor.set(ControlMode.PercentOutput,IntakeConstants.kIntakeMotorStopSpeed);
  }


}
