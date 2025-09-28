import Members from './Members'
import Expenses from './Expenses'
import Settlements from './Settlements'

export default function Main({summaryData}) {
    const members = summaryData.hasOwnProperty('members') ? summaryData.members : {}
    const balances = summaryData.hasOwnProperty('balances') ? summaryData.balances : {}

    const expenses = summaryData.hasOwnProperty('expenses') ? summaryData.expenses : []

    const debts = summaryData.hasOwnProperty('debts') ? summaryData.debts : []
    return (
            <main>
                <section className="flex-row">
                    <Expenses expenses={expenses} />
                </section>
                <section className="flex-row" style={{width: '100%'}}>
                    <Members members={members} balances={balances}/>
                    <Settlements debts={debts} />
                </section>
            </main>
        )
}