package vulcan.examples

import cats.Eq
import org.scalacheck.{Arbitrary, Gen}
import vulcan.{AvroError, Codec}

sealed trait SealedTraitEnum

final object SealedTraitEnum {
  implicit final val arbitrary: Arbitrary[SealedTraitEnum] =
    Arbitrary(Gen.oneOf(FirstInSealedTraitEnum, SecondInSealedTraitEnum))

  implicit final val eq: Eq[SealedTraitEnum] =
    Eq.fromUniversalEquals

  implicit final val codec: Codec[SealedTraitEnum] =
    Codec.enum(
      name = "SealedTraitEnum",
      namespace = Some("vulcan.examples"),
      symbols = List("first", "second"),
      aliases = List("first", "second"),
      doc = Some("documentation"),
      default = Some(FirstInSealedTraitEnum),
      encode = {
        case FirstInSealedTraitEnum  => "first"
        case SecondInSealedTraitEnum => "second"
      },
      decode = {
        case "first"  => Right(FirstInSealedTraitEnum)
        case "second" => Right(SecondInSealedTraitEnum)
        case other    => Left(AvroError(other))
      }
    )
}

final case object FirstInSealedTraitEnum extends SealedTraitEnum

final case object SecondInSealedTraitEnum extends SealedTraitEnum
