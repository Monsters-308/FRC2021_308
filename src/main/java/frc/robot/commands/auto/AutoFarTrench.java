package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.DriveTurn;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.shooter.ShootSpeed;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * A complex auto command that starts the shooter drives forward, Backward, Forward intakes 2 balls Aims and shoots .
 */
public class AutoFarTrench extends SequentialCommandGroup {
  /**
   * Creates a new AutoNearTrench.
   *
   * @param drive The drive subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   * @param intake The intake subsystem this command will run on
   * @param hopper The hopper subsystem this command will run on
   */
  public AutoFarTrench(DriveSubsystem drive, IndexerSubsystem indexer, ShooterSubsystem shooter,IntakeSubsystem intake, HopperSubsystem hopper) {
    addCommands(
      new ParallelCommandGroup(
        new SequentialCommandGroup(
          new DriveDistance(                                //Drive 30 Inches
            Constants.AutoConstants.kAutoFarTrenchDistance,
            Constants.AutoConstants.kAutoFarTrenchSpeed,
            drive),
          new DriveTurn(//Turn 90 Degrees(works, turns a few times and stops at 90, infinate turns) Change Turn to 1 and test if it doesnt work
            m_gyro.reset(), // will it stop at 88/92 or at exactly 90?
            while abs(m_gyro.getAngle()) != 88<=92 {
              Constants.AutoConstants.kAutoFarTrenchTurn,
            Constants.AutoConstants.kAutoFarTrenchTurnSpeed,
            abs(m_gyro.getAngle())
            } 
            Constants.AutoConstants.kAutoFarTrenchTurnStop,
            m_gyro.reset(),

            /* //(works,turns a few times and stops at 90, infinate turns)
            m_gyro.reset(),
            if m_gyro.getAngle() == 90 {
              Constants.AutoConstants.kAutoFarTrenchTurnStop,
            }else{
              m_gyro.getAngle()
            Constants.AutoConstants.kAutoFarTrenchTurn,
            Constants.AutoConstants.kAutoFarTrenchTurnSpeed,
            }

            */
            ),
          new DriveDistance(                                //Drive 30 Inches
          Constants.AutoConstants.kAutoFarTrenchDistance,
          Constants.AutoConstants.kAutoFarTrenchSpeed,
            drive),
          new DriveTurn(                                    //Turn 90 Degrees
          m_gyro.reset(),
          while m_gyro.getAngle() != 88||89||90|91||92 {
            Constants.AutoConstants.kAutoFarTrenchTurn,
          Constants.AutoConstants.kAutoFarTrenchTurnSpeed,
          } 
          m_gyro.reset(),
            ),
          new DriveDistance(                                //Drive 30 Inches

            drive)
        )
      )
    );
  }

}
