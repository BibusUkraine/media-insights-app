
type Button ={
    text?:string,
    image?:string,
    className?:string
    imageStyle?:string
}
const Button = ({text,image,className,imageStyle}:Button) => {
    return (
        <div className={`w-fit  rounded-full border-primary bg-secondary border-2  mx-2 ${className}`}>
            {image &&<img src={image} className={`w-[24px] h-[24px] ${imageStyle}`} alt={text+' image'}/>}
            {text &&  <p className={'text-white ml-2'}>{text}</p>}
        </div>
    );
};

export default Button;