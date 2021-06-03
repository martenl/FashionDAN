import React, { useState } from 'react';
import styles from './CreateCampaign.module.css';
import { gql, useMutation } from '@apollo/client';
import { UploadCreative } from '../uploadCreative/UploadCreative';
import DatePicker from 'react-datepicker';

import "react-datepicker/dist/react-datepicker.css";


//createCampaign(name: String!)
const CREATE_CAMPAIGN = gql`
  mutation createCampaign($name:String!) {
    createCampaign(name:$name) {
      id
    }
  }
`;

export function CreateCampaign() {
    const [campaignName, setCampaignName] = useState('');
    const [startDate, setStartDate] = useState(new Date());

    const [createCampaign, { data }] = useMutation(CREATE_CAMPAIGN);

    if (data ) {
        console.log(data);
    }

    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Create campaign:
                </span>
                <input
                    className={styles.textbox}
                    aria-label="Set campaign name"
                    placeholder="Set campaign name"
                    value={campaignName}
                    onChange={e => setCampaignName(e.target.value)}
                />
                <DatePicker
                      showTimeSelect
                      selected={startDate}
                      onChange={date => {if(date && date instanceof Date){setStartDate(date)}}}
                    />
                <button
                    className={styles.asyncButton}
                    onClick={() => createCampaign({ variables: { name:campaignName+startDate.toString()} })}
                >
                    Create Campaign
                </button>
            </div>
            <UploadCreative/>
        </div >
    );
}