import Members from './Members'
import Expenses from './Expenses'

export default function Main({summaryData}) {
    const members = summaryData.hasOwnProperty('members') ? summaryData.members : {}
    const balances = summaryData.hasOwnProperty('balances') ? summaryData.balances : {}

    const expenses = summaryData.hasOwnProperty('expenses') ? summaryData.expenses : []

    return (
            <main>
                <section className="flex-row">
                    <Expenses expenses={expenses} />
                </section>
                <section className="flex-row" style={{width: '100%'}}>
                    <Members members={members} balances={balances}/>
                    <div className="card" style={{width: '50%'}}></div>
                </section>
            </main>
        )
}