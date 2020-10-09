# CarInfo-Test

Debug Apk: https://drive.google.com/file/d/14sZ1vd_YKS8AVxysv_wHVcMjJ9yqEtql/view?usp=sharing

# Design Decisions & Dependencies:
## Kotlin:
I ❤️ Kotlin. It's a breath of fresh air when coming from Java, and makes code so much nicer to read and write. It has OOTB support for Lambdas, Extension Functions, DSLs and a vast stdlib. JetBrains actively maintains and releases stable versions every month. All my projects (including this one) will be 100% Kotlin.

## JetPack - Architecture Components & AndroidX:
Would be a loss to build an application without these libraries. With Google advocating MVVM, and these libraries working so flawlessly with each other, it really leaves you no choice.
Room - Database Layer

<b>ViewModel</b> - Data Preservation across configuration changes

<b>Lifecycle</b> - Handling annoying issues with Activities / Fragments namely when pushing data during false states

<b>Navigation</b> - Handling Intent / Fragment Transactions, isolating sources from destinations and easy argument passing!

<b>AndroidX, Material Components</b> - For embracing Material Design and backporting API features to minSdk

<b>Paging 3</b> - sets up a Repository that will use the local database to page in data for the UI and also back-fill the database from the network as the user reaches to the end of the data in the database.

## Koin - Dependency Injection:
Sick and tired of Dagger in Production, and annoyed by it slowing down my build, I turned to a substitute. Koin seemed to be the recommended (and a more established) library for Android, however I chose Koin for it's sheer simplicity. It also made strides in performance in v2.0, which makes it my current choice for DI without code generation.
also made strides in performance , which makes it my current choice for DI without code generation.

## Gson + Retrofit - Networking:
A very simple choice when it comes to using REST APIs.

