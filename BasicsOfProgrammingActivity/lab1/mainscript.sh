#!/bin/sh

# 1 task
mkdir lab0
cd lab0
mkdir blitzle4
mkdir cubchoo6
touch donphan5
touch eelektross6
touch lickilicky3
mkdir machop5
cd blitzle4
mkdir victreebel
touch kingdra
mkdir crobat
mkdir quilava
cd ..
cd cubchoo6
mkdir grotle
touch scrafty
touch samurott
touch vullaby
touch hypno
cd ..
cd machop5
mkdir crobat
mkdir tentacruel
mkdir bellossom
cd ..
cd blitzle4
echo -e "Возможности Overland=5 Surface=12 Sky=7 Underwater14=0\nJump=1 Power=5 Intelligence=5 Aura=0 Fountain=0 Gilled=0" >> kingdra
cd ..
cd cubchoo6
echo -e "Способности Sand-Attack Faint Attack Headbutt\nSwagger Brick Break Payback Chip Away Hi Jump Kick Scary Face Crunch\nFacade Rock Climb Focus Punch Head Smash" >> scrafty
echo -e "Способности Tail\nWhip Water Gun Water Sport Focus Energy Razor Shell Fury Cutter Water\nPulse Revenge Aqua Jet Slash Encore Aqua Tail Retaliate Swords Dance Hydro\nPump" >> samurott
echo -e "Способности Gust Leer Fury Attack Pluck Nasty\nPlot Flatter Faint Attack Punishment Defog Tailwind Air Slash Dark\nPulse Embargo Whirlwind Brave Bird Mirror Move" >> vullaby
echo -e "Тип покемона\nPSYCHIC NONE" >> hypno
cd ..
echo "Тип диеты Herbivore" >> donphan5
echo -e "Ходы Acid\nAqua Tail Bind Bounce Charge Beam Crunch Crush Claw Discharge Drain Punch Fire Punch Gastro Acid Giga Drain Headbutt Iron Tail Knock Off Magnet Rise Signal Beam Sleep Talk Snore Spark Super Fang\nSuperpower Thunder Wave" >> eelektross6
echo -e "Ходы Aqua Tail Bind Block\nCounter Double-Edge Dynamicpunch Fire Punch Focus Punch Ice Punch Icy\nWind Iron Tail Knock Off Mega Kick Mega punch Mud-Slap Rock Climb\nRollout Seismic Toss Shock Wave Sleep Talk Snore Thunderpunch Water\nPulse Zen Headbutt" >> lickilicky3

# 2 task
chmod 317 blitzle4
chmod u=rwx,g=wx,o=rw ./blitzle4/victreebel
chmod u=r,g=r ./blitzle4/victreebel
chmod u=rx,g=wx,o=rwx machop5/crobat
chmod 307 blitzle4/quilava
chmod u=rwx,g=rw,o=r ./cubchoo6/
chmod u=rwx,g=wx,o=rwx ./cubchoo6/grotle
chmod o=rw ./cubchoo6/scrafty
chmod 404 ./cubchoo6/samurott
chmod u=r ./cubchoo6/vullaby
chmod u=r,o=r ./cubchoo6/hypno
chmod 044 donphan5
chmod u=r eelektross6 
chmod u=r,g=r lickilicky3 
chmod 330 machop5/
chmod u=rwx,g=rw,o=r ./machop5/crobat
chmod u=rwx,g=wx,o=wx ./machop5/tentacruel
chmod 355 ./machop5/bellossom

# 3 task
cat cubchoo6/hypno blitzle4/kingdra > eelektross6_95
ln -s eelektross6_95 cubchoo6/scraftyeelektross
cp eelektross6 machop5/bellossom
sudo cat donphan5 > cubchoo6/samurottdomphan
ln -s cubchoo6/ Copy_16
ln lickilicky3 cubchoo6/vullabylicklicky
sudo cp -r blitzle4/ machop5/bellossom

# 4 task

# 1 ex:
find . -type f -exec wc -l {} + 2>/dev/null | sort -n

# 2 ex:
sudo ls blitzle4/ | sort -r | 2>/tmp/file.txt

# 3 ex:
sudo ls blitzle4/ | sort -r | 2>tmp/file.txt

# 4 ex:
ls -r1 | sort

# 5 ex
grep . -r --include="\c*" ./ | sort | 2>&1

# 6 ex
ls -lR | grep "li"| sort -k 2 -r | tail -n 4 | 2>&1 cat

# 5 task
rm eelektross6
rm ./blitzle4/kingdra
rm cubchoo6/scraftyeelektro*
rm cubchoo6/vullabylickilic*
rm -r blitzle4
rm blitzle4/quilava
