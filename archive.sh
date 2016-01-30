rm -rf data-*/target
myfile=$(date +'%Y%m%d_SWTK-DATA-%s.tar.gz')
tar cvf $myfile .
#mv $myfile $EOD
