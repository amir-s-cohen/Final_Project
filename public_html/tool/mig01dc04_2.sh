ssh urm@mig01.impl.alma.dc04.hosted.exlibrisgroup.com << EOF
        dinst 01pnwl_inst_testload
        go output
        echo "Number of Holdings in HOL DIR:"
        find HOL -name '*xml' | wc -l

EOF >> mig01dc04_output.sh
csh -fx mig01dc04_output.sh
