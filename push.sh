#!/bin/bash
msg="$1"
if [[ -z "$msg" ]]; then 
    echo "You must have message to commit our code"
    exit 1
fi

git add .
git commit -m "$msg"
git push -u origin main