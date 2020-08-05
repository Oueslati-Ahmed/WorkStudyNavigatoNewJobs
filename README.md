# Work Study Navigator New Jobs Finder
Simple java script that analyses the current jobs on the uOttawa work study navigator and displays the new jobs u didnt see yet.

* Start by searching the available jobs on the WSP from uoZone
* On the top of the search result table click export, this will save the results as a csv file
* Move the Jobs.csv file to the same folder as the script (don't change the csv file name)
* Run the Run.bat file and you will see the jobs you havent seen already
* Download another CSV file and replace the old Jobs.csv with it, the script will only show newly added jobs

The point of this program is to keep track of the jobs that are posted recently, since the navigator doesnt show which jobs you applied to while looking at active job posting, the navigator doesnt sort jobs by the date they were added it. They're ranked randomly. And most of us would take any job we can fill, so the more jobs we apply to the better

# How it works

For the first run, the script reads the file and extracts the job numbers only, then it maps them into a hashmap. And since its the first run every job is going to be considered new or unseen and will be displayed
After that all these job numbers are going to be saved into the `data.properties` file

For the next runs, the script maps the job numbers from the new CSV file into the hashmap `hash1` , then it loads the saved file `data.properties` and maps it to the hashmap `hash2` , then using an iterator it checks for the keys in `hash1` that are not present in `hash2` (new jobs) and displays them and appends them to the `data.properties` file using the `save()` method


