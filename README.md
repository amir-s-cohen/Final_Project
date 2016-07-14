# Pre and Post Migration To Alma Sanity Checks - בדיקות תקינות נתונים טרם לאחר הסבה לאלמה 

## Getting Started 

These instructions will help access and use the the dedicated AMO internal team site for the Migration To Alma Sanity Checks. See deployment for notes on how to deploy the project on a live system.

### Prerequisities

1. You must be an Alma Migration Operations team members - either an Analyst or a Programmer.
2. Send you IP to Amir Cohen, who will add it to the allowed IPs of this system.

### Using the System

1. Once confirmed by Amir that your IP was added to the allowed IPs, access the il-almaimp01/~amirc/ 
2. In order to run the Sanity checks do the following:
    a. Search for you project in the dedicated search field - institution code oriented.
    b. Once you get the project's details results use them in the next step.
    c. Click on the "Migration Sanity Checks" link, and access the Sanity checks form page.
    d. Once you reached the "Migration Sanity Checks" form, upload your project's Delivered Files list.
    e. Choose the relevant Migration and Implemenation servers.
    f. Enter the Migration environment name and institution ID.
    g. Choose the desired Migration Sanity Check and click on its button.
    h. Wait for the system to run the requested job.
    i. See the requested Sanity Checks results.
    j. If need, use the EXPORT button to export the results to a Microsoft Excel downloadable file.
    
And repeat

until finished


End with an example of getting some data out of the system or using it for a little demo


## Deployment

Add additional notes about how to deploy this when running on a live Migration project:

1. When getting search results, make sure to use the relevant migration's information - TestLoad or Cutover. Usually both Migration type results will be available.
2. You should your most updated Delivered Files list file, which can only be a Microsoft Excel (.xlsx) file.
3. For re-searching projects, you must access the AMO internal site home page at il-almaimp01/~amirc/.

## Built With

* Java - 1.6 SDK
* HTML 5
* PHP 2
* JavaScript
* jQuery
* mySQL
* Bash
* Perl

## Author

* **Amir Cohen** - *Initial work* - [Amir Cohen's Final Project](https://github.com/amir-s-cohen/Final_Project)


## Acknowledgments

* Shay Tavor (Academic Instructor)
* Chaim Elbaum (Professional Instructor)
* Michael Privorotsky - AMO team member
