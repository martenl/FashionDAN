import React, { useState } from 'react';
import styles from './UploadCreative.module.css';
import { gql, useMutation } from '@apollo/client';


const UPLOAD_CREATIVE = gql`
  mutation uploadCreative($creativeFile: Upload!) {
    uploadCreative(creativeFile:$creativeFile)
  }
`;

export function UploadCreative() {

    const [uploadCreative] = useMutation(UPLOAD_CREATIVE);

    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Upload creative:
                </span>
                <input
                  type="file"
                  placeholder="Choose a file"
                  onChange={(e:React.ChangeEvent<HTMLInputElement>) => {
                      if(e.target.files != null && e.target.files[0] != null) {
                          uploadCreative({variables: {creativeFile: e.target.files[0]}});
                      }
                  }}/>
                <button
                    className={styles.asyncButton}
                    onClick={() => console.log('hello button')}
                >
                    Upload creative
                </button>
            </div>
        </div >
    );
}