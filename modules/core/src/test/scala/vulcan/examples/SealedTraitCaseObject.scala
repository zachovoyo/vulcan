package vulcan.examples

import vulcan.Codec

sealed trait SealedTraitCaseObject

final case object CaseObjectInSealedTrait extends SealedTraitCaseObject

object SealedTraitCaseObject {
  implicit val codec: Codec[SealedTraitCaseObject] =
    Codec.derive
}
