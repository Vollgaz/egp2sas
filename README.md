# egp2sas
Extract code from EGP project to make a single SAS programm

# Prérequis
EGP file must have been executed at least once and integrally

# Usage
--src target a file or a folder :
- FILE : if EGP file , ask for conversion
            if something else, show an empty list //TO FIX
- FOLDER : Scan folder and subfolders for all files with EGP extension

-- dest target the output folder

> Usage: egp2sas [options] \
>  --help                 Print help. \
>  -f, --src <filepath>   can be file or folder. \
>  -o, --dest <filepath>  must be a folder.\
>  -y, --no-prompt        hide processing confirmation.

# TODO
Add fake test resources