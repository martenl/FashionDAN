import React, { useState } from 'react';
import styles from './StartCampaign.module.css';
import { gql, useMutation } from '@apollo/client';


const START_CAMPAIGN = gql`
  mutation startCampaign($id:ID!,$startDate: Int!) {
    startCampaign(id:$id, startDate:$startDate)
  }
`;


export function StartCampaign() {
    const [campaignId, setCampaignId] = useState(123);
    const [startDate, setStartDate] = useState(5);

    const [startCampaign, { data }] = useMutation(START_CAMPAIGN);

    if (data ) {
        console.log(data);
    }

    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Start campaign:
                </span>
                <input
                    className={styles.textbox}
                    aria-label="Set campaign id"
                    value={campaignId}
                    onChange={e => setCampaignId(Number(e.target.value) || 1)}
                />
                <input
                    className={styles.textbox}
                    aria-label="Set start date"
                    value={startDate}
                    onChange={e => setStartDate(Number(e.target.value) || 1)}
                />
                <button
                    className={styles.asyncButton}
                    onClick={() => startCampaign({ variables: { id: campaignId, startDate: startDate} })}
                >
                    Start Campaign
                </button>
            </div>
        </div >
    );
}