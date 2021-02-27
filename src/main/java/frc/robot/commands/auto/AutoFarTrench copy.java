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
// import frc.robot.subsystems.HopperSubsystem;
// import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.subsystems.IndexerSubsystem;
// import frc.robot.subsystems.IntakeSubsystem;

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

  public AutoFarTrench(DriveSubsystem drive) {
    addCommands(
        // new ParallelCommandGroup(
          new SequentialCommandGroup(
            new DriveDistance(                             
              Constants.AutoConstants.kAutoJerkDistance, 
              Constants.AutoConstants.kAutoJerkForwardSpeed,
              true,
              drive),
              new DriveTurn(                             
              Constants.AutoConstants.kAutoFarTrenchTurn, 
              Constants.AutoConstants.kAutoFarTrenchTurnRightSpeed,
              drive),
            new DriveDistance(               
              Constants.AutoConstants.kAutoJerkDistance,
              Constants.AutoConstants.kAutoJerkReverseSpeed,
              true,
              drive),
            new DriveTurn(                                
                Constants.AutoConstants.kAutoFarTrenchTurn,
                Constants.AutoConstants.kAutoFarTrenchTurnRightSpeed,
                drive),
            new DriveDistance(                
              Constants.AutoConstants.kAutoFarTrenchReverseDistance,
              Constants.AutoConstants.kAutoFarTrenchReverseSpeed,
              true,
              drive)
          )
      // )
    );
  }

}
