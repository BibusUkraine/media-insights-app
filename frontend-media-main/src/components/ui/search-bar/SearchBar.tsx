import {useState} from 'react'
import SearchIcon from '../../../assets/search.svg'

type SearchBarProps = {
    className?: string
    onSearch:()=> void

}
const SearchBar = ({className,onSearch}:SearchBarProps) => {
    const [input,setInput] = useState('');
    const [resize, setResize] = useState(false)
    return (
        <form className={`${className} relative w-[280px] flex items-center  sm:w-[60px] md:${resize ? 'w-[280px]' : 'w-[60px]'}`} onSubmit={(e)=>onSearch(e,input.toLowerCase())}>
            <input onChange={(e)=>setInput(e.target.value)} type={'text'}  placeholder={window.innerWidth >= 1000 ? 'Search...' : ''} className={'w-full p-2 px-3 flex items-center  rounded-full border-primary bg-secondary border-2 mx-2 text-white placeholder:text-white'} maxLength={20}/>
            <img src={SearchIcon} className={'absolute right-[20px] w-[24px] h-[24px]'} onClick={()=>setResize(!resize)}/>
        </form>

    );
};

export default SearchBar;