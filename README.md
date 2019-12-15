# artificer-svc
Spring Boot application to expose Fate/Grand Order data for the Artificer application. Artificer is an application meant to help Masters plan their battles in the Fate/Grand Order mobile game.

## Setting up environment
1. `bash install_prerequisites`
2. `gradle wrapper`
3. `./gradlew init`
4. `./gradlew clean build`

## MVP - Materials Calculator
Keeps track of what servants you have, which ones you want, the materials required, and the best places to find them.

### Requirements

#### Database
- Login system
- 5* Servants only
- Servant name
- Servant class
- Servant skills (1-10)
- Servant skill materials
- Servant ascension materials
- Materials found in current Spreadsheet only
- Material names
- Material images
- Artificer material quantity
- Artificer material needed
- Artificer servants (owned)
- Artificer servants skills (owned)
- Artificer servants (wanted)
- Artificer servants skills (wanted)
- Move from Owned -> Wanted
- Move from Wanted -> Owned
- Add to Owned/Wanted
- Remove from Owned/Wanted
- View Owned/Wanted (toggle with buttons)
- Materials NEED to be in order of game, not spreadsheet

## Future - 1-Turn Max Damage Calculator

### Requirements

#### Database
- 5*s only
- Servant name
- Servant attribute
- Servant alignment
- Servant cards
- Servant attack stats (base, max, grail)
- Servant hp stats (base, max, grail)
- Servant NP damage (1-5) (include rank ups)
- Servant NP effect
- Servant NP Overcharge effect (1-5)
- Servant special skills
- Servant image (Stage 1 only)
- Servant class
- Servant skills (include rank ups)
- Servant star absorption
- Servant star generation per hit
