package Presentation.GUI.AdminGUI;

import Presentation.GUI.WrapLayout;
import Presentation.Listeners.EventItemSelected;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class RoomView extends JPanel
{
    private ArrayList<RoomData> items = new ArrayList<RoomData>();
    private Color effectColor;
    private Point point;
    private float[] dist = {0.0f, 0.5f, 1.0f};
    private Color[] colors;
    private int itemWidth = 150;
    private int itemHeight = 150;
    private int space = 4;
    private int lineSize = 4;
    private int showLine = 1;
    private EventItemSelected event;
    private int etaj;

    RoomView(int width, int height)
    {
        setSize(width, height);
        setLayout(new WrapLayout(WrapLayout.LEFT, space + lineSize * 2, space + lineSize * 2));
        setOpaque(false);
        setEffectColor(new Color(255, 255, 255));
        setForeground(new Color(205, 205, 205));

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                point = me.getPoint();
                checKMouse();
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                point = me.getPoint();
                checKMouse();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                point = null;
                checKMouse();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (event != null && point != null) {
                    int index = getMouseOver();
                    if(me.getButton() == MouseEvent.BUTTON1)
                    {
                        if (index >= 0)
                        {
                            event.selected(items.get(index).id, items.get(index).tipCamera);
                        }
                    }
                    if(me.getButton() == MouseEvent.BUTTON3)
                    {
                        event.removeStudentFromRoom(items.get(index).id, items.get(index).students, items.get(index).studentsCNP);
                    }

                }
            }
        });
    }

    private void initItem()
    {
        removeAll();
        ArrayList<RoomData> aux = new ArrayList<>();
        for(RoomData d : items)
        {
            if(d.etaj == etaj)
                aux.add(d);
        }
        items = aux;

        for (RoomData d : items)
        {
            add(createRoom(d));
        }
        repaint();
        revalidate();
    }

    private JComponent createRoom(Object data)
    {
        JPanel room = new JPanel();
        room.setPreferredSize(new Dimension(itemWidth - lineSize * 2, itemWidth - lineSize * 2));
        RoomData d = (RoomData) data;

        if(d.students.size() != 0)
        {//set color based on sex
            if(Objects.equals(d.tipCamera, "F"))
                room.setBackground(new Color(171, 21, 236));
            else if(Objects.equals(d.tipCamera, "M"))
                room.setBackground(new Color(61, 61, 238));
            //else room.setBackground(new Color(100, 100, 100));
        }
        else
        {//empty room
            room.setBackground(new Color(39, 39, 39));

        }

        JLabel camera = new JLabel("Camera " + d.nrCamera);
        camera.setForeground(getForeground());

        JLabel number = new JLabel(d.students.size() + "/" + d.nrLocuri);
        number.setForeground(getForeground());

        JLabel cameraComuna = new JLabel("(" + d.cameraComuna + ")");
        if(d.cameraComuna != 0) cameraComuna.setForeground(getForeground());
        else cameraComuna.setForeground(room.getBackground());

        String students = "<html>";
        for (String s : d.students)
        {
            students += s + "<br>";
        }
        students += "</html>";
        JLabel labelStudenti = new JLabel(students);
        labelStudenti.setForeground(getForeground());

        room.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        room.add(cameraComuna,c);

        c.anchor = GridBagConstraints.PAGE_START;
        room.add(camera, c);

        c.anchor = GridBagConstraints.FIRST_LINE_END;
        room.add(number,c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        room.add(labelStudenti, c);

        return room;
    }

    private JLabel createLabel(Object data) {
        JLabel label = new JLabel("<html>" + data.toString().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(getForeground());
        label.setPreferredSize(new Dimension(itemWidth, itemHeight));
        return label;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
        int red = effectColor.getRed();
        int green = effectColor.getGreen();
        int blue = effectColor.getBlue();
        colors = new Color[]{new Color(red, green, blue, 70), new Color(red, green, blue, 20), new Color(red, green, blue, showLine)};
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Shape mouseOver = null;
        int width = getWidth();
        int height = getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = space * 2;
        int y = space * 2;
        int size = 150;
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).etaj != etaj) continue;
            Shape s = createShape(x, y, itemWidth, itemHeight);
            if (mouseOver == null && isMouseOver(x, y, itemWidth, itemHeight)) {
                mouseOver = s;
            }
            g2d.fill(s);
            x += space + itemWidth;
            if (x + space + itemWidth > width) {
                x = space * 2;
                y += space + itemHeight;
            }
        }
        g2d.setComposite(AlphaComposite.SrcIn);
        if (point != null)
        {
            RadialGradientPaint p = new RadialGradientPaint(point, size, dist, colors);
            g2d.setPaint(p);
        }
        else
        {
            g2d.setColor(new Color(255, 255, 255, showLine));
        }
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        if (mouseOver != null) {
            g2d.setColor(effectColor);
            g2d.fill(mouseOver);
        }
        g2d.dispose();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        super.paint(g);
    }

    private Shape createShape(int x, int y, int widht, int height) {
        Area area = new Area(new Rectangle2D.Double(x, y, widht, height));
        area.subtract(new Area(new Rectangle2D.Double(x + lineSize, y + lineSize, widht - lineSize * 2, height - lineSize * 2)));
        return area;
    }

    private boolean isMouseOver(int x, int y, int width, int height) {
        boolean over = false;
        if (point != null) {
            if (new Rectangle2D.Double(x, y, width, height).contains(point)) {
                over = true;
            }
        }
        return over;
    }

    private int getMouseOver() {
        int width = getWidth();
        int x = space * 2;
        int y = space * 2;
        for (int i = 0; i < items.size(); i++)
        {
            if (isMouseOver(x, y, itemWidth, itemHeight)) {
                return i;
            }
            x += space + itemWidth;
            if (x + space + itemWidth > width) {
                x = space * 2;
                y += space + itemHeight;
            }
        }
        return -1;
    }

    private void checKMouse() {
        if (point == null) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            int index = getMouseOver();
            if (index == -1) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }

    public ArrayList getItems()
    {
        return items;
    }

    public void setItems(ArrayList items, int etaj) {
        this.items = items;
        this.etaj = etaj;
        initItem();
    }

    public EventItemSelected getEvent() {
        return event;
    }

    public void setEvent(EventItemSelected event) {
        this.event = event;
    }
}
