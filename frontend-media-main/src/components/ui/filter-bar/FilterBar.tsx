import {useDispatch,useSelector} from "react-redux";
import SettingsSvg from '../../../assets/settings.svg';
import {addFilter, removeFilter} from "../../../store/slices/app";
import {RootState} from "../../../store/store";
import Back from '../../../assets/back.svg'

type FilterBarProps = {
    topics: string[]
    setClicked: () => void
    clicked: boolean
}

const FilterBar = ({topics,setClicked,clicked}:FilterBarProps) => {
    const selectedFilters = useSelector((state:RootState) => state.app.filter);
    const dispatch = useDispatch()


    const onSelectFilter = (topic:string) => {
        if ((selectedFilters as string[]).includes(topic)) {
            dispatch(removeFilter(topic));
        } else {
            dispatch(addFilter(topic));
        }
    }


    return (
        <>
            <div className={`w-fit min-w-[165px] p-2 px-3 flex items-center cursor-pointer rounded-full border-primary bg-secondary border-2  mx-5 z-30`}
                 onClick={setClicked}>
                <img src={clicked ? SettingsSvg : Back} className={`w-[24px] h-[24px]`} alt= {'image'}/>
                <p className={'text-white ml-2 select-none'}>{clicked ? 'Feed settings' : 'Back'}</p>
            </div>
            <span className={`${clicked ? 'bg-quaternary rounded-full duration-300 absolute ' : 'fixed bg-primary opacity-85 scale-[5000%] duration-500 '} min-w-[164px] h-[43px] flex  translate-x-[148px] z-[5]   justify-center items-center`}></span>
            <div className={` ${clicked ? 'hidden' : 'flex'} fixed flex-col w-full h-full top-0 z-20 items-center justify-evenly md:top-[10%]`}>
                <h1 className={'text-3xl text-white md:text-2xl sm:text-xl'}>Pick tags what relevant to you :)</h1>
                <div className={'flex  w-[40%] items-center justify-center flex-wrap mb-[70px] md:w-[60%] sm:w-[60%]'}>
                    {topics.map((topic: string, index: number) => {
                        return (
                            <button
                                key={index}
                                className={`p-2 px-3 rounded-md m-2 ${
                                    (selectedFilters as string[]).includes(topic)
                                        ? ' bg-emptyImage text-white'
                                        : 'bg-quinary'
                                }`}
                                onClick={() => onSelectFilter(topic)}
                            >
                                <p>{topic}</p>
                            </button>
                        )
                    })}
                </div>
            </div>
        </>
    );
};

export default FilterBar;